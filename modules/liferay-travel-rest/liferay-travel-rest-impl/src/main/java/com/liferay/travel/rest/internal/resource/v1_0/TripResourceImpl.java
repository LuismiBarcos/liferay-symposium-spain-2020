package com.liferay.travel.rest.internal.resource.v1_0;

import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.util.ActionUtil;
import com.liferay.travel.constants.TravelsConstants;
import com.liferay.travel.rest.dto.v1_0.Trip;
import com.liferay.travel.rest.resource.v1_0.StageResource;
import com.liferay.travel.rest.resource.v1_0.TripResource;

import com.liferay.travel.service.TripService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Javier de Arcos
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/trip.properties",
	scope = ServiceScope.PROTOTYPE, service = TripResource.class
)
public class TripResourceImpl extends BaseTripResourceImpl {

	@Override
	public Page<Trip> getTripsPage() {
		return Page.of(getTripsActions(), tripService.getTrips().stream().map(this::toTrip).collect(Collectors.toList()));
	}

	private Map<String, Map<String, String>> getTripsActions() {
		return HashMapBuilder
				.put("get", addAction("VIEW", "getTripsPage", TravelsConstants.RESOURCE_NAME, contextUser.getGroupId()))
				.put("create", addAction("ADD_ENTRY", "postTrip", TravelsConstants.RESOURCE_NAME, contextUser.getGroupId()))
				.build();
	}

	@Override
	public Trip getTrip(@NotNull Long tripId) throws Exception {
		return toTrip(tripService.getTrip(tripId));
	}

	@Override
	public Trip postTrip(Trip trip) throws Exception {
		return toTrip(tripService.addTrip(contextUser.getGroupId(), contextUser.getUserId(),
				trip.getName(), trip.getDescription(), trip.getStartingDate(), trip.getImage()));
	}

	@Override
	public Trip putTrip(@NotNull Long tripId, Trip trip) throws Exception {
		return toTrip(tripService.updateTrip(contextUser.getGroupId(), contextUser.getUserId(),
				tripId, trip.getName(), trip.getDescription(), trip.getStartingDate(), trip.getImage()));
	}

	@Override
	public void deleteTrip(@NotNull Long tripId) throws Exception {
		tripService.deleteTrip(tripId);
	}

	private Trip toTrip(com.liferay.travel.model.Trip trip) {
		Trip tripResource = new Trip();
		tripResource.setActions(getTripActions(trip));
		tripResource.setId(trip.getTripId());
		tripResource.setName(trip.getName());
		tripResource.setDescription(trip.getDescription());
		tripResource.setStartingDate(trip.getStartingDate());
		tripResource.setImage(trip.getImage());

		return tripResource;
	}

	private Map<String, Map<String, String>> getTripActions(com.liferay.travel.model.Trip trip) {
		return HashMapBuilder
				.put("get", addAction("VIEW", trip, "getTrip"))
				.put("add_stage",
						ActionUtil.addAction("ADD_STAGE", StageResource.class, trip, "postTripStage",
								contextScopeChecker, contextUriInfo))
				.put("update", addAction("UPDATE", trip, "putTrip"))
				.put("delete", addAction("DELETE", trip, "deleteTrip"))
				.build();
	}

	@Reference
	private TripService tripService;
}