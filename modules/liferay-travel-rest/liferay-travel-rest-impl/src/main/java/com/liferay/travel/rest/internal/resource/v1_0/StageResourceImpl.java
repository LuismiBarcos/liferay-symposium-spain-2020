package com.liferay.travel.rest.internal.resource.v1_0;

import com.liferay.travel.rest.dto.v1_0.Stage;
import com.liferay.travel.rest.resource.v1_0.StageResource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import javax.validation.constraints.NotNull;

/**
 * @author Javier de Arcos
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/stage.properties",
	scope = ServiceScope.PROTOTYPE, service = StageResource.class
)
public class StageResourceImpl extends BaseStageResourceImpl {

	@Override
	public void deleteStage(@NotNull Long stageId) throws Exception {
		super.deleteStage(stageId);
	}

	@Override
	public Stage getStage(@NotNull Long stageId) throws Exception {
		return super.getStage(stageId);
	}

	@Override
	public Stage putStage(@NotNull Long stageId, Stage stage) throws Exception {
		return super.putStage(stageId, stage);
	}

	@Override
	public Stage postTripStage(@NotNull Long tripId, Stage stage) throws Exception {
		return super.postTripStage(tripId, stage);
	}
}