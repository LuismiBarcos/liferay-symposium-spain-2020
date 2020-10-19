package com.liferay.travel.rest.internal.resource.v1_0;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.search.filter.TermFilter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.SearchUtil;
import com.liferay.travel.rest.dto.v1_0.Trip;
import com.liferay.travel.rest.internal.odata.entity.v1_0.TripEntityModel;
import com.liferay.travel.rest.resource.v1_0.TripResource;

import com.liferay.travel.service.TripService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.MultivaluedMap;
import java.util.stream.Collectors;

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
	public Page<Trip> getTripsPage(String search, Filter filter) throws Exception {
		return SearchUtil.search(
				null, booleanQuery -> {},
				filter, com.liferay.travel.model.Trip.class, search, null,
				queryConfig -> queryConfig.setSelectedFieldNames(Field.ENTRY_CLASS_PK),
				searchContext -> searchContext.setCompanyId(contextCompany.getCompanyId()),
				null,
				document -> toTrip(tripService.getTrip(GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))));
	}

	@Override
	public Trip getTrip(@NotNull Long tripId) throws Exception {
		return toTrip(tripService.getTrip(tripId));
	}

	@Override
	public Trip postTrip(Trip trip) throws PortalException {
		return toTrip(tripService.addTrip(
			trip.getName(),
			trip.getDescription(),
			trip.getStartingDate(),
			trip.getImage(),
			contextUser.getUserId(),
			contextUser.getGroupId()));
	}

	@Override
	public Trip putTrip(@NotNull Long tripId, Trip trip) throws Exception {
		return toTrip(tripService.updateTrip(
			tripId,
			trip.getName(),
			trip.getDescription(),
			trip.getStartingDate(),
			trip.getImage(),
			contextUser.getUserId(),
			contextUser.getGroupId()));
	}

	@Override
	public void deleteTrip(@NotNull Long tripId) throws Exception {
		tripService.deleteTrip(tripId);
	}

	private Trip toTrip(com.liferay.travel.model.Trip trip) {
		Trip tripResource = new Trip();
		tripResource.setId(trip.getTripId());
		tripResource.setName(trip.getName());
		tripResource.setDescription(trip.getDescription());
		tripResource.setStartingDate(trip.getStartingDate());
		tripResource.setImage(trip.getImage());

		return tripResource;
	}

	@Reference
	private TripService tripService;
}