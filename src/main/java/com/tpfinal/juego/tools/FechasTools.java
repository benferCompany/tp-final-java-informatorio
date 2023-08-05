package com.tpfinal.juego.tools;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
@Getter @Setter
@AllArgsConstructor
public class FechasTools {
    public Date convertirStringADate(String fecha) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = sdf.parse(fecha);
        return fechaDate;
    }

    public Date starOfDay(Date fecha){
        Calendar startOfDay = Calendar.getInstance();
        startOfDay.setTime(fecha);
        startOfDay.set(Calendar.HOUR_OF_DAY, 0);
        startOfDay.set(Calendar.MINUTE, 0);
        startOfDay.set(Calendar.SECOND, 0);
        startOfDay.set(Calendar.MILLISECOND, 0);
        return startOfDay.getTime();

    }
    public Date endOfDay(Date fecha){
        Calendar endOfDay = Calendar.getInstance();
        endOfDay.setTime(fecha);
        endOfDay.set(Calendar.HOUR_OF_DAY, 23);
        endOfDay.set(Calendar.MINUTE, 59);
        endOfDay.set(Calendar.SECOND, 59);
        endOfDay.set(Calendar.MILLISECOND, 999);
        return endOfDay.getTime();
    }

}
