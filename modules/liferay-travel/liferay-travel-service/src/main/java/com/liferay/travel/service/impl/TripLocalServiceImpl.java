/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.travel.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.travel.exception.NoSuchTripException;
import com.liferay.travel.model.Trip;
import com.liferay.travel.service.base.TripLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the trip local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.travel.service.TripLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TripLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.liferay.travel.model.Trip",
	service = AopService.class
)
public class TripLocalServiceImpl extends TripLocalServiceBaseImpl {

	public List<Trip> getTrips() {
		return tripPersistence.findAll();
	}

	public Trip getTrip(long tripId) throws PortalException {
		return tripPersistence.findByPrimaryKey(tripId);
	}

	public Trip addTrip(String name, String description, Date startingDate, String image) {
		long tripId = counterLocalService.increment();

		Trip newTrip = tripPersistence.create(tripId);
		newTrip.setName(name);
		newTrip.setDescription(description);
		newTrip.setStartingDate(startingDate);
		newTrip.setImage(image);

		return tripPersistence.update(newTrip);
	}

	public Trip updateTrip(long tripId, String name, String description, Date startingDate, String image)
			throws PortalException {
		Trip trip = tripPersistence.findByPrimaryKey(tripId);
		trip.setName(name);
		trip.setStartingDate(startingDate);
		trip.setDescription(description);
		trip.setImage(image);

		return tripPersistence.update(trip);
	}

	public Trip deleteTrip(long tripId) throws PortalException {
		return tripPersistence.remove(tripId);
	}
}