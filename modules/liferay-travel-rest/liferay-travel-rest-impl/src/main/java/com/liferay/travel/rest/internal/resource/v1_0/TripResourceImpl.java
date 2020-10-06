package com.liferay.travel.rest.internal.resource.v1_0;

import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.travel.rest.dto.v1_0.Stage;
import com.liferay.travel.rest.dto.v1_0.Trip;
import com.liferay.travel.rest.resource.v1_0.TripResource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

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
		return Page.of(_tripService.getTrips());
	}

	@Override
	public Trip getTrip(@NotNull Long tripId) {
		return _tripService.getTrip(tripId);
	}

	private TripService _tripService = new TripService();

	private static class TripService {
		public Set<Trip> getTrips() {
			return Collections.singleton(_exampleTrip);
		}

		public Trip getTrip(long id) {
			return _exampleTrip;
		}

		private Trip _exampleTrip = new Trip() {{
			id = 1L;
			name = "Summer Team Building";
			startingDate = new Date();
			stages = Collections.singleton(new Stage() {{
				id = 1L;
				name = "Throw axes";
				description = "What a better team building activity than throwing axes to your teammates";
				place = "Madrid";
			}}).toArray(new Stage[0]);
		}};
	}
}