package com.paymentpoint.data.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * La clase {@code DateUtils} proporciona utilidades para trabajar con fechas.
 * Puede utilizarse para agregar días a una fecha, encontrar el primer lunes a
 * partir de una fecha dada y más.
 */
public final class DateUtils {

    private DateUtils() {
    }

    /**
     * Agrega una semana a una fecha dada.
     *
     * @param inputDate La fecha de entrada.
     * @return Una nueva fecha que es 7 días después de la fecha de entrada.
     */
    public static Date addOneWeekToDate(Date inputDate) {
        return addDaysToDate(inputDate, 7);
    }

    /**
     * Encuentra el primer lunes a partir de una fecha dada.
     *
     * @param input La fecha de entrada.
     * @return La fecha correspondiente al primer lunes.
     */
    public static Date getFirstMondayFromDate(Date input) {

        if (input == null) {
            return getFirstMondayFromDate(new Date());
        }

        int index = getDayOfWeekIndex(input);
        index = daysForNextDay(index, Calendar.MONDAY);

        return addDaysToDate(input, index);
    }

    private static int daysForNextDay(int indexDayPrev, int indexDayPost) {

        if (indexDayPrev < 1 || indexDayPrev > 7 ||
                indexDayPost < 1 || indexDayPost > 7)
            return -1;

        int result = 7 + indexDayPost - indexDayPrev;

        if (result > 7)
            result -= 7;

        return result;
    }

    /**
     * Obtiene el índice del día de la semana (1 para domingo, 2 para lunes, etc.)
     * de una fecha dada.
     *
     * @param input La fecha de entrada.
     * @return El índice del día de la semana.
     */
    public static int getDayOfWeekIndex(Date input) {
        calendar.setTime(input);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Agrega una cantidad específica de días a una fecha dada.
     *
     * @param input La fecha de entrada.
     * @param days  La cantidad de días a agregar.
     * @return La nueva fecha resultante después de agregar los días.
     */
    public static Date addDaysToDate(Date input, int days) {
        calendar.setTime(input);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    /**
     * Compara dos fechas y calcula si ambas fechas corresponden al mismo día o no.
     *
     * @param d1 La fecha a partir de la que se va a comparar.
     * @param d2 Fecha a comparar.
     * @return Regresa -1 si la segunda fecha es anterior a la primera, 0 si ambas son iguales y 1 si la segunda fecha es posterior a la primera.
     */
    public static int compareDate(Date d1, Date d2) {
        calendar.setTime(d1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long md1 = calendar.getTimeInMillis();
        calendar.setTime(d2);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long md2 = calendar.getTimeInMillis();
        return Long.compare(md1, md2);
    }

    private static final Calendar calendar = Calendar.getInstance();
}
