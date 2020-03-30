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

  public <Any> Any toAny(Object t, Class<Any> anyClass) {
    return modelMapper.map(t, anyClass);
  }

  public D toDto(T t) {
    return toAny(t, dtoClazz);
  }

  public List<D> toDtoList(List<T> tList) {

    return toList(tList, dtoClazz);
  }

  public <Any> List<Any> toList(List<?> tList, Class<Any> anyClass) {
    return tList.stream()
        .map(m -> modelMapper.map(m, anyClass))
        .collect(Collectors.toList());
  }

}
