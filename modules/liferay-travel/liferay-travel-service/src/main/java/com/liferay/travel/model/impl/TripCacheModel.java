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

package com.liferay.travel.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.travel.model.Trip;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Trip in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TripCacheModel implements CacheModel<Trip>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TripCacheModel)) {
			return false;
		}

		TripCacheModel tripCacheModel = (TripCacheModel)object;

		if (tripId == tripCacheModel.tripId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, tripId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", tripId=");
		sb.append(tripId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", startingDate=");
		sb.append(startingDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Trip toEntityModel() {
		TripImpl tripImpl = new TripImpl();

		if (uuid == null) {
			tripImpl.setUuid("");
		}
		else {
			tripImpl.setUuid(uuid);
		}

		tripImpl.setTripId(tripId);

		if (name == null) {
			tripImpl.setName("");
		}
		else {
			tripImpl.setName(name);
		}

		if (startingDate == Long.MIN_VALUE) {
			tripImpl.setStartingDate(null);
		}
		else {
			tripImpl.setStartingDate(new Date(startingDate));
		}

		tripImpl.resetOriginalValues();

		return tripImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		tripId = objectInput.readLong();
		name = objectInput.readUTF();
		startingDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(tripId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeLong(startingDate);
	}

	public String uuid;
	public long tripId;
	public String name;
	public long startingDate;

}