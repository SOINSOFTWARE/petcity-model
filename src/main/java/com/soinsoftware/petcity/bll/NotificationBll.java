// Soin Software, 2018
package com.soinsoftware.petcity.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

	public List<Notification> select(Company company, LocalDate localDate) {
		LocalDateTime initialDate = buildLocalDateTime(localDate, 0, 0, 0);
		LocalDateTime finalDate = buildLocalDateTime(localDate, 23, 59, 59);
		List<Notification> notifications = ((NotificationDao) dao).select(company,
				Date.from(initialDate.atZone(ZoneId.systemDefault()).toInstant()),
				Date.from(finalDate.atZone(ZoneId.systemDefault()).toInstant()));
		return notifications.stream()
				.sorted(Comparator.comparing(notification -> ((Notification) notification).getNotificationDate()))
				.collect(Collectors.toList());
	}

	private LocalDateTime buildLocalDateTime(LocalDate localDate, int hourOfDay, int minuteOfHour, int secondOfMinute) {
		return LocalDateTime.of(localDate, LocalTime.of(hourOfDay, minuteOfHour, secondOfMinute));
	}

	public static NotificationBll getInstance() throws IOException {
		if (instance == null) {
			instance = new NotificationBll();
		}
		return instance;
	}
}