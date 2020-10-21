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

package com.liferay.travel.internal.security.permission.resource.definition;

import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.StagedModelPermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.definition.ModelResourcePermissionDefinition;
import com.liferay.travel.constants.TravelsConstants;
import com.liferay.travel.model.Stage;
import com.liferay.travel.service.StageLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.function.Consumer;

/**
 * @author Javier de Arcos
 */
@Component(
        immediate = true,
        service = ModelResourcePermissionDefinition.class
)
public class StageModelResourcePermissionDefinition implements ModelResourcePermissionDefinition<Stage> {
    @Override
    public Stage getModel(long primaryKey) throws PortalException {
        return _stageLocalService.getStage(primaryKey);
    }

    @Override
    public Class<Stage> getModelClass() {
        return Stage.class;
    }

    @Override
    public PortletResourcePermission getPortletResourcePermission() {
        return _portletResourcePermission;
    }

    @Override
    public long getPrimaryKey(Stage stage) {
        return stage.getStageId();
    }

    @Override
    public void registerModelResourcePermissionLogics(
            ModelResourcePermission<Stage> modelResourcePermission,
            Consumer<ModelResourcePermissionLogic<Stage>> modelResourcePermissionLogicConsumer) {
        modelResourcePermissionLogicConsumer.accept(
                new StagedModelPermissionLogic<>(
                        _stagingPermission,
                        "com_liferay_travel_web_TravelPortlet",
                        Stage::getStageId));
    }

    @Reference
    private StageLocalService _stageLocalService;

    @Reference(target = "(resource.name=" + TravelsConstants.RESOURCE_NAME + ")")
    private PortletResourcePermission _portletResourcePermission;

    @Reference
    private StagingPermission _stagingPermission;
}
