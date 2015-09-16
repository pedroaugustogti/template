package br.com.template.util;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.template.domain.Tempo;



/**
 * The Class DataUtil.
 */
public final class DataUtil {

	
	private DataUtil(){
	}
	
	public static int ano(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	public static int mes(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH);
	}

	public static int hora(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	public static int dia(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	public static Date criaData(int ano, int mes, int dia) {
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.YEAR, ano);
		calendar.set(Calendar.MONTH, mes);
		calendar.set(Calendar.DAY_OF_MONTH, dia);
		
		return calendar.getTime();
	}
	
	public static Date criaData(int ano, int mes) {
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.YEAR, ano);
		calendar.set(Calendar.MONTH, mes);
		calendar.set(Calendar.DAY_OF_MONTH, BigInteger.ZERO.intValue());
		
		return calendar.getTime();
	}
	
	public static Date criaData(int ano) {
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.YEAR, ano);
		calendar.set(Calendar.MONTH, BigInteger.ZERO.intValue());
		calendar.set(Calendar.DAY_OF_MONTH, BigInteger.ZERO.intValue());
		
		return calendar.getTime();
	}
	
	public static int maiorDiaDoMes(int ano, int mes) {
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.YEAR, ano);
		calendar.set(Calendar.MONTH, mes);
		
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public static Date getHoraInicialDia() {
		
		Calendar date = Calendar.getInstance();
		
		date.set(Calendar.HOUR_OF_DAY, Tempo.HORA.getTempoMinimo());
		date.set(Calendar.MINUTE, Tempo.MINUTO.getTempoMinimo());
		date.set(Calendar.SECOND, Tempo.SEGUNDO.getTempoMinimo());
		
		return date.getTime();
	}
	
	public static Calendar getCalendarHoraInicialDia() {
		
		Calendar date = Calendar.getInstance();
		
		date.set(Calendar.HOUR_OF_DAY, Tempo.HORA.getTempoMinimo());
		date.set(Calendar.MINUTE, Tempo.MINUTO.getTempoMinimo());
		date.set(Calendar.SECOND, Tempo.SEGUNDO.getTempoMinimo());
		
		return date;
	}

	public static Date getHoraFinalDia() {
		
		Calendar date = Calendar.getInstance();
		
		date.set(Calendar.HOUR_OF_DAY, Tempo.HORA.getTempoMaximo());
		date.set(Calendar.MINUTE, Tempo.MINUTO.getTempoMaximo());
		date.set(Calendar.SECOND, Tempo.SEGUNDO.getTempoMaximo());
		
		return date.getTime();
	}
	
	public static Calendar getCalendarHoraFinalDia() {
		
		Calendar date = Calendar.getInstance();
		
		date.set(Calendar.HOUR_OF_DAY, Tempo.HORA.getTempoMaximo());
		date.set(Calendar.MINUTE, Tempo.MINUTO.getTempoMaximo());
		date.set(Calendar.SECOND, Tempo.SEGUNDO.getTempoMaximo());
		
		return date;
	}

	@SuppressWarnings("deprecation")
	public static void setMenorHora(Date dataInicio) {
		
		dataInicio.setHours(Tempo.HORA.getTempoMinimo());
		dataInicio.setMinutes(Tempo.MINUTO.getTempoMinimo());
		dataInicio.setSeconds(Tempo.SEGUNDO.getTempoMinimo());
	}
	
	@SuppressWarnings("deprecation")
	public static void setMaiorHora(Date dataInicio) {
		
		dataInicio.setHours(Tempo.HORA.getTempoMaximo());
		dataInicio.setMinutes(Tempo.MINUTO.getTempoMaximo());
		dataInicio.setSeconds(Tempo.SEGUNDO.getTempoMaximo());
	}

	public static String minimoDoisDigitos(int dia) {
		return dia < 10 ? "0"+dia : String.valueOf(dia);
	}

	@SuppressWarnings("deprecation")
	public static void setHora(Date data, int hora) {
		data.setHours(hora);
	}

	public static Date criaData(long tempoOcioso) {
		
		Calendar date = Calendar.getInstance();
		
		date.set(Calendar.MILLISECOND, (int)tempoOcioso);
		
		return date.getTime();
	}

	public static String converteMilisegundosEmTempoFormatado(long milisegundos) {
		
		return new SimpleDateFormat("mm:ss").format(milisegundos);
	}
}