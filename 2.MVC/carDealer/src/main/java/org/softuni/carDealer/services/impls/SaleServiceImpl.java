package org.softuni.carDealer.services.impls;

import org.softuni.carDealer.domain.dtos.binding.add.SaleAddDto;
import org.softuni.carDealer.domain.dtos.view.SaleWithCarView;
import org.softuni.carDealer.domain.entities.Sale;
import org.softuni.carDealer.repositories.SaleRepository;
import org.softuni.carDealer.services.apis.SaleService;
import org.softuni.carDealer.utils.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SaleServiceImpl implements SaleService<Sale, Long> {
    private final SaleRepository saleRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository carRepository) {
        this.saleRepository = carRepository;
    }

    @Override
    public void saveSaleDto(SaleAddDto saleAddDto) {
        Sale sale = ModelParser.getInstance().map(saleAddDto, Sale.class);
        this.saleRepository.saveAndFlush(sale);
    }

    @Override
    public List<SaleWithCarView> allSalesWithCars() {
        List<Sale> sales = this.saleRepository.findAllByOrderByDiscountAsc();
        return convertSalesToDTOs(sales);
    }

    @Override
    public SaleWithCarView salesWithCarsById(Long id) {
        Optional<Sale> saleOptional = this.saleRepository.findById(id);
        SaleWithCarView saleWithCarView = null;
        if(saleOptional.isPresent()) {
            saleWithCarView = (ModelParser.getInstance().map(saleOptional.get(), SaleWithCarView.class));
        }
        return saleWithCarView;
    }

    @Override
    public List<SaleWithCarView> allDiscountedSalesWithCars() {
        List<Sale> sales = this.saleRepository.findAllByDiscountGreaterThanOrderByDiscountAsc(0.0);
       return convertSalesToDTOs(sales);
    }

    @Override
    public List<SaleWithCarView> allDiscountedSalesWithCarsWithPercent(Double percent) {
        List<Sale> sales = this.saleRepository.findAllByDiscountEqualsOrderByDiscountAsc(percent);
        return convertSalesToDTOs(sales);
    }

    private List<SaleWithCarView> convertSalesToDTOs(List<Sale> sales){
        List<SaleWithCarView> saleWithCarViews = new ArrayList<>();
        for (Sale sale : sales) {
            SaleWithCarView saleWithCarView = ModelParser.getInstance().map(sale, SaleWithCarView.class);
            saleWithCarViews.add(saleWithCarView);
        }
        return saleWithCarViews;
    }
}
