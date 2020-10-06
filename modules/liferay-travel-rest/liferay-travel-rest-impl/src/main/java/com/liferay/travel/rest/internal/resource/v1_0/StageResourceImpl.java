package com.liferay.travel.rest.internal.resource.v1_0;

import com.liferay.travel.rest.dto.v1_0.Stage;
import com.liferay.travel.rest.resource.v1_0.StageResource;

import com.liferay.travel.service.StageService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
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
	public Stage getStage(@NotNull Long stageId) throws Exception {
		return toStage(stageService.getStage(stageId));
	}

	@Override
	public Stage postTripStage(@NotNull Long tripId, Stage stage) {
		return toStage(stageService.addStage(tripId, stage.getName(), stage.getDescription(), stage.getPlace()));
	}

	@Override
	public Stage putStage(@NotNull Long stageId, Stage stage) throws Exception {
		return toStage(stageService.updateStage(stageId, stage.getName(), stage.getDescription(), stage.getPlace()));
	}

	@Override
	public void deleteStage(@NotNull Long stageId) throws Exception {
		stageService.deleteStage(stageId);
	}

	private Stage toStage(com.liferay.travel.model.Stage stage) {
		Stage stageResource = new Stage();

		stage.setName(stage.getName());
		stage.setDescription(stage.getDescription());
		stage.setPlace(stage.getPlace());

		return stageResource;
	}

	@Reference
	private StageService stageService;
}