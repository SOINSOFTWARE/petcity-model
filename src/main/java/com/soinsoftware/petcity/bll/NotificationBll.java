// Soin Software, 2018
package com.soinsoftware.petcity.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

import com.soinsoftware.petcity.dao.NotificationDao;
import com.soinsoftware.petcity.model.Company;
import com.soinsoftware.petcity.model.Notification;
import com.soinsoftware.petcity.model.Pet;

/**
 * @author Carlos Rodriguez
 * @since 10/12/2018
 */
public class NotificationBll extends AbstractBll<Notification, BigInteger> {

	private static NotificationBll instance;

	private NotificationBll() throws IOException {
		super(new NotificationDao());
	}

	public List<Notification> select(Pet pet) {
		List<Notification> notifications = ((NotificationDao) dao).select(pet);
		return notifications.stream()
				.sorted(Comparator.comparing(notification -> ((Notification) notification).getNotificationDate()))
				.collect(Collectors.toList());
	}

	public List<Notification> select(Company company) {
		List<Notification> notifications = ((NotificationDao) dao).select(company);
		return notifications.stream()
				.sorted(Comparator.comparing(notification -> ((Notification) notification).getNotificationDate()))
				.collect(Collectors.toList());
	}

	public List<Notification> select(Company company, Date notificationDate) {
		Date initialDate = buildDate(notificationDate, 0, 0, 0);
		Date finalDate = buildDate(notificationDate, 23, 59, 59);
		List<Notification> notifications = ((NotificationDao) dao).select(company, initialDate, finalDate);
		return notifications.stream()
				.sorted(Comparator.comparing(notification -> ((Notification) notification).getNotificationDate()))
				.collect(Collectors.toList());
	}

	private Date buildDate(Date date, int hourOfDay, int minuteOfHour, int secondOfMinute) {
		DateTime dateTime = new DateTime(date.getTime());
		int year = dateTime.getYear();
		int monthOfYear = dateTime.getMonthOfYear();
		int dayOfMonth = dateTime.getDayOfMonth();
		return new DateTime(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute).toDate();
	}

	public static NotificationBll getInstance() throws IOException {
		if (instance == null) {
			instance = new NotificationBll();
		}
		return instance;
	}
}