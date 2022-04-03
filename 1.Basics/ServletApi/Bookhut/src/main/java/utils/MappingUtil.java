package utils;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class MappingUtil {

    public static <S, D> D  convert(S source, Class<D> destinationClass){
        ModelMapper mapper = new ModelMapper();
        return source != null ? mapper.map(source, destinationClass) : null;
    }
    public static <S,D> List<D> convert(List<S> sourceList, Class<D> destinationClass){
        List<D> resultList = new ArrayList<>();
        for (S sourceObject : sourceList) {
            resultList.add(convert(sourceObject, destinationClass));
        }
        return resultList;
    }

    public static <S,D> Set<D> convert(Set<S> sourceList, Class<D> destinationClass){
        Set<D> resultList = new HashSet<>();
        for (S sourceObject : sourceList) {
            resultList.add(convert(sourceObject, destinationClass));
        }
        return resultList;
    }
}
