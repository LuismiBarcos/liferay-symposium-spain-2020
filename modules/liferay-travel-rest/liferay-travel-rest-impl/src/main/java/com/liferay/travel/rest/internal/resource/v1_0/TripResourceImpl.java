package com.liferay.travel.rest.internal.resource.v1_0;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.ActionUtil;
import com.liferay.portal.vulcan.util.SearchUtil;
import com.liferay.travel.constants.TravelsConstants;
import com.liferay.travel.rest.dto.v1_0.Trip;
import com.liferay.travel.rest.internal.odata.entity.v1_0.TripEntityModel;
import com.liferay.travel.rest.resource.v1_0.TripResource;

import com.liferay.travel.service.TripService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.MultivaluedMap;
import java.util.Map;

/**
 * @author Javier de Arcos
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/trip.properties",
	scope = ServiceScope.PROTOTYPE, service = TripResource.class
)
public class TripResourceImpl extends BaseTripResourceImpl implements EntityModelResource {

	@Override
	public EntityModel getEntityModel(MultivaluedMap<?, ?> multivaluedMap) throws Exception {
		return new TripEntityModel();
	}

	@Override
	public Page<Trip> getTripsPage(String search, Filter filter, Pagination pagination, Sort[] sorts) throws Exception {
		return SearchUtil.search(
				_getTripsActions(), booleanQuery -> {},
				filter, com.liferay.travel.model.Trip.class, search, null,
				queryConfig -> queryConfig.setSelectedFieldNames(Field.ENTRY_CLASS_PK),
				searchContext -> searchContext.setCompanyId(contextCompany.getCompanyId()),
				null,
				document -> toTrip(tripService.getTrip(GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))));
	}

	private Map<String, Map<String, String>> _getTripsActions() {
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
						ActionUtil.addAction("ADD_STAGE", StageResourceImpl.class, trip, "postTripStage",
								contextScopeChecker, contextUriInfo))
				.put("update", addAction("UPDATE", trip, "putTrip"))
				.put("delete", addAction("DELETE", trip, "deleteTrip"))
				.build();
	}

	@Reference
	private TripService tripService;
}