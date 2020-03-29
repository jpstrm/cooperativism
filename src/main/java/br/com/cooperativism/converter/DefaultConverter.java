package br.com.cooperativism.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Component
public abstract class DefaultConverter<T extends Serializable, D extends Serializable> {

  private Class<T> clazz;
  private Class<D> dtoClazz;

  @Autowired
  protected ModelMapper modelMapper;

  void setClazz(Class<T> clazz) {
    this.clazz = clazz;
  }

  void setDtoClazz(Class<D> dtoClazz) {
    this.dtoClazz = dtoClazz;
  }

  public D toDto(T t) {
    return modelMapper.map(t, dtoClazz);
  }

  public List<D> toDtoList(List<T> tList) {
    return tList.stream()
        .map(m -> modelMapper.map(m, dtoClazz))
        .collect(Collectors.toList());
  }

}
