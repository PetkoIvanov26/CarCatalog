package com.petkoivanov.carCatalog.exceptions;

public class EntityAlreadyExistsException extends RuntimeException{
  public EntityAlreadyExistsException(String message){
    super(message);
  }
}
