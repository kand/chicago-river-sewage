package com.hydrophilik.mwrdCsoScraper.parsing;

import org.joda.time.DateTime;

import com.hydrophilik.mwrdCsoScraper.utils.DateTimeUtils;

public class CsoEvent {
	private DateTime startTime = null;
	private DateTime endTime = null;
	private String outfallLocation = null;
	private int waterwaySegment;

	public CsoEvent(DateTime startTime, DateTime endTime,
			String outfallLocation, int waterwaySegment) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.outfallLocation = outfallLocation;
		this.waterwaySegment = waterwaySegment;
	}

	public boolean isValid() {
		if (null == startTime)
			return false;
		return true;
	}
	
	public String parseToString() {
		
		long millisBetweenStartAndEnd = endTime.getMillis() - startTime.getMillis();
		String secondsBetweenStartAndEnd = Long.toString(millisBetweenStartAndEnd / 60000);

		return outfallLocation + ";" + waterwaySegment + ";" + startTime.toLocalDate().toString() +
				";" + startTime.toLocalTime().toString() + ";" + endTime.toLocalTime().toString() + ";" +
				secondsBetweenStartAndEnd;

	}
	
	public String getSqlInsert() {
		long millisBetweenStartAndEnd = endTime.getMillis() - startTime.getMillis();
		String secondsBetweenStartAndEnd = Long.toString(millisBetweenStartAndEnd / 60000);

		return "INSERT INTO CSOs VALUES (NULL, '" + outfallLocation + "'," + waterwaySegment + ",'" +
				startTime.toLocalDate().toString() + "','" + DateTimeUtils.getTimeAsHoursMins(startTime.toLocalTime()) +
				"','" + DateTimeUtils.getTimeAsHoursMins(endTime.toLocalTime()) + "'," +
				secondsBetweenStartAndEnd + ")";

	}
	
}
