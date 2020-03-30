package br.com.cooperativism.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApiHelper {

  public static LocalDateTime getNow() {
    final java.time.LocalDateTime localDateTime = LocalDateTime.now();
    final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm");
    final String formatted = localDateTime.format(dateTimeFormatter);
    return LocalDateTime.parse(formatted, dateTimeFormatter);
  }

  public static boolean isDateExpired(LocalDateTime dateTime, LocalDateTime dateTime2) {
    return dateTime.compareTo(dateTime2) > 0;
  }

}
