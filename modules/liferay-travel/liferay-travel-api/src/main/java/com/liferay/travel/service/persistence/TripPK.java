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

package com.liferay.travel.service.persistence;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TripPK implements Comparable<TripPK>, Serializable {

	public long tripId;
	public long resourcePrimKey;

	public TripPK() {
	}

	public TripPK(long tripId, long resourcePrimKey) {
		this.tripId = tripId;
		this.resourcePrimKey = resourcePrimKey;
	}

	public long getTripId() {
		return tripId;
	}

	public void setTripId(long tripId) {
		this.tripId = tripId;
	}

	public long getResourcePrimKey() {
		return resourcePrimKey;
	}

	public void setResourcePrimKey(long resourcePrimKey) {
		this.resourcePrimKey = resourcePrimKey;
	}

	@Override
	public int compareTo(TripPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (tripId < pk.tripId) {
			value = -1;
		}
		else if (tripId > pk.tripId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (resourcePrimKey < pk.resourcePrimKey) {
			value = -1;
		}
		else if (resourcePrimKey > pk.resourcePrimKey) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TripPK)) {
			return false;
		}

		TripPK pk = (TripPK)object;

		if ((tripId == pk.tripId) && (resourcePrimKey == pk.resourcePrimKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, tripId);
		hashCode = HashUtil.hash(hashCode, resourcePrimKey);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(6);

		sb.append("{");

		sb.append("tripId=");

		sb.append(tripId);
		sb.append(", resourcePrimKey=");

		sb.append(resourcePrimKey);

		sb.append("}");

		return sb.toString();
	}

}