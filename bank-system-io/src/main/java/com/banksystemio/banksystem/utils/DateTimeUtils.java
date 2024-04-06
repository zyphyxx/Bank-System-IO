package com.banksystemio.banksystem.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    // Define o formato desejado para a data e hora
    private static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";

    // Cria um objeto DateTimeFormatter com o formato desejado
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    // Método para formatar LocalDateTime usando o DateTimeFormatter definido
    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(FORMATTER);
    }

    // Método para fazer o parsing de uma string para LocalDateTime usando o DateTimeFormatter definido
    public static LocalDateTime parseLocalDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, FORMATTER);
    }
}
