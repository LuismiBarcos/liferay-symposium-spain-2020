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

package com.liferay.travel.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StageService}.
 *
 * @author Brian Wing Shun Chan
 * @see StageService
 * @generated
 */
public class StageServiceWrapper
	implements ServiceWrapper<StageService>, StageService {

	public StageServiceWrapper(StageService stageService) {
		_stageService = stageService;
	}

	@Override
	public com.liferay.travel.model.Stage addStage(
		long tripId, String name, String description, String place) {

		return _stageService.addStage(tripId, name, description, place);
	}

	@Override
	public com.liferay.travel.model.Stage deleteStage(long stageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _stageService.deleteStage(stageId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _stageService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.travel.model.Stage getStage(long stageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _stageService.getStage(stageId);
	}

	@Override
	public java.util.List<com.liferay.travel.model.Stage> getStages(
		long tripId) {

		return _stageService.getStages(tripId);
	}

	@Override
	public com.liferay.travel.model.Stage updateStage(
			long stageId, String name, String description, String place)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _stageService.updateStage(stageId, name, description, place);
	}

	@Override
	public StageService getWrappedService() {
		return _stageService;
	}

	@Override
	public void setWrappedService(StageService stageService) {
		_stageService = stageService;
	}

	private StageService _stageService;

}