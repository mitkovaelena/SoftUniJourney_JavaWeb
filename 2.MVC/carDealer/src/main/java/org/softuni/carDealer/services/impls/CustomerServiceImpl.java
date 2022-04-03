package org.softuni.carDealer.services.impls;

import org.softuni.carDealer.domain.dtos.binding.add.CustomerAddDto;
import org.softuni.carDealer.domain.dtos.binding.relations.CustomerDto;
import org.softuni.carDealer.domain.dtos.view.CustomerView;
import org.softuni.carDealer.domain.dtos.view.TotalCustomerSalesView;
import org.softuni.carDealer.domain.entities.Car;
import org.softuni.carDealer.domain.entities.Customer;
import org.softuni.carDealer.domain.entities.Sale;
import org.softuni.carDealer.repositories.CustomerRepository;
import org.softuni.carDealer.services.apis.CustomerService;
import org.softuni.carDealer.utils.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService<Customer, Long> {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository carRepository) {
        this.customerRepository = carRepository;
    }

    @Override
    public void save(CustomerAddDto customerAddDto) throws ParseException {
        Customer customer = new Customer();
        this.convertCustomerAddDtoToCustomer(customerAddDto, customer);
        customer.setSales(new HashSet<>());
        this.customerRepository.save(customer);
    }

    @Override
    public List<CustomerView> findAllOrderedCustomers(String order) {  // Asc or Desc by birth_date
        List<Customer> customers = new ArrayList<>();
        if (order.equals("ascending")) {
            customers = this.customerRepository.findAllByOrderByBirthDateAscYoungDriverAsc();
        } else if (order.equals("descending")) {
            customers = this.customerRepository.findAllByOrderByBirthDateDescYoungDriverAsc();
        } else {
            //ToDo ?
        }

        List<CustomerView> customerViews = new ArrayList<>();
        for (Customer customer : customers) {
            customerViews.add(ModelParser.getInstance().map(customer, CustomerView.class));
        }
        return customerViews;
    }

    @Override
    public TotalCustomerSalesView totalCustomerSales(Long id) {
        Optional<Customer> customerOptional = this.customerRepository.findById(id);
        TotalCustomerSalesView customerSalesView = null;
        if (customerOptional.isPresent()) {
            customerSalesView = new TotalCustomerSalesView();
            customerSalesView.setName(customerOptional.get().getName());
            customerSalesView.setBoughtCars(customerOptional.get().getSales()
                    .stream().map(Sale::getCar).distinct().mapToInt(x -> 1).sum());
            customerSalesView.setSpentMoney(customerOptional.get().getSales()
                    .stream().map(Sale::getCar).map(Car::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add));
        }
        return customerSalesView;
    }

    @Override
    public CustomerView findCustomerById(Long id) {
        Optional<Customer> customerOptional = this.customerRepository.findById(id);
        CustomerView customerView = null;
        if (customerOptional.isPresent()) {
            customerView = ModelParser.getInstance().map(customerOptional.get(), CustomerView.class);
        }
        return customerView;
    }

    @Override
    public void editCustomer(Long id, CustomerAddDto customerToAdd) throws ParseException {
        Customer customer = this.customerRepository.findById(id).get();
        this.convertCustomerAddDtoToCustomer(customerToAdd, customer);
        this.customerRepository.save(customer);
    }

    private void convertCustomerAddDtoToCustomer(CustomerAddDto customerAddDto, Customer customer) throws ParseException {
        customer.setName(customerAddDto.getName());

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date birthDate = df.parse(customerAddDto.getBirthDate());
        customer.setBirthDate(birthDate);
        Calendar date = Calendar.getInstance();
        date.setTime(birthDate);
        date.add(Calendar.YEAR, 20);  //assuming that the driver got a license when turning 18
        customer.setYoungDriver(date.before(new Date()));
    }
}
