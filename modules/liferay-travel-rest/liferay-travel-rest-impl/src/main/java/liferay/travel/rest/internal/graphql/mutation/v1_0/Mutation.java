package liferay.travel.rest.internal.graphql.mutation.v1_0;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;

import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import liferay.travel.rest.dto.v1_0.Stage;
import liferay.travel.rest.dto.v1_0.Trip;
import liferay.travel.rest.resource.v1_0.StageResource;
import liferay.travel.rest.resource.v1_0.TripResource;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Javier de Arcos
 * @generated
 */
@Generated("")
public class Mutation {

	public static void setStageResourceComponentServiceObjects(
		ComponentServiceObjects<StageResource>
			stageResourceComponentServiceObjects) {

		_stageResourceComponentServiceObjects =
			stageResourceComponentServiceObjects;
	}

	public static void setTripResourceComponentServiceObjects(
		ComponentServiceObjects<TripResource>
			tripResourceComponentServiceObjects) {

		_tripResourceComponentServiceObjects =
			tripResourceComponentServiceObjects;
	}

	@GraphQLField
	public Stage createTripStage(
			@GraphQLName("tripId") Long tripId,
			@GraphQLName("stage") Stage stage)
		throws Exception {

		return _applyComponentServiceObjects(
			_stageResourceComponentServiceObjects,
			this::_populateResourceContext,
			stageResource -> stageResource.postTripStage(tripId, stage));
	}

	@GraphQLField
	public Response createTripStageBatch(
			@GraphQLName("tripId") Long tripId,
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_stageResourceComponentServiceObjects,
			this::_populateResourceContext,
			stageResource -> stageResource.postTripStageBatch(
				tripId, callbackURL, object));
	}

	@GraphQLField
	public boolean deleteStage(@GraphQLName("stageId") Long stageId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_stageResourceComponentServiceObjects,
			this::_populateResourceContext,
			stageResource -> stageResource.deleteStage(stageId));

		return true;
	}

	@GraphQLField
	public Response deleteStageBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_stageResourceComponentServiceObjects,
			this::_populateResourceContext,
			stageResource -> stageResource.deleteStageBatch(
				callbackURL, object));
	}

	@GraphQLField
	public Stage updateStage(
			@GraphQLName("stageId") Long stageId,
			@GraphQLName("stage") Stage stage)
		throws Exception {

		return _applyComponentServiceObjects(
			_stageResourceComponentServiceObjects,
			this::_populateResourceContext,
			stageResource -> stageResource.putStage(stageId, stage));
	}

	@GraphQLField
	public Response updateStageBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_stageResourceComponentServiceObjects,
			this::_populateResourceContext,
			stageResource -> stageResource.putStageBatch(callbackURL, object));
	}

	@GraphQLField
	public Trip createTrip(@GraphQLName("trip") Trip trip) throws Exception {
		return _applyComponentServiceObjects(
			_tripResourceComponentServiceObjects,
			this::_populateResourceContext,
			tripResource -> tripResource.postTrip(trip));
	}

	@GraphQLField
	public Response createTripBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_tripResourceComponentServiceObjects,
			this::_populateResourceContext,
			tripResource -> tripResource.postTripBatch(callbackURL, object));
	}

	@GraphQLField
	public boolean deleteTrip(@GraphQLName("tripId") Long tripId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_tripResourceComponentServiceObjects,
			this::_populateResourceContext,
			tripResource -> tripResource.deleteTrip(tripId));

		return true;
	}

	@GraphQLField
	public Response deleteTripBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_tripResourceComponentServiceObjects,
			this::_populateResourceContext,
			tripResource -> tripResource.deleteTripBatch(callbackURL, object));
	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private <T, E1 extends Throwable, E2 extends Throwable> void
			_applyVoidComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeConsumer<T, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			unsafeFunction.accept(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(StageResource stageResource)
		throws Exception {

		stageResource.setContextAcceptLanguage(_acceptLanguage);
		stageResource.setContextCompany(_company);
		stageResource.setContextHttpServletRequest(_httpServletRequest);
		stageResource.setContextHttpServletResponse(_httpServletResponse);
		stageResource.setContextUriInfo(_uriInfo);
		stageResource.setContextUser(_user);
	}

	private void _populateResourceContext(TripResource tripResource)
		throws Exception {

		tripResource.setContextAcceptLanguage(_acceptLanguage);
		tripResource.setContextCompany(_company);
		tripResource.setContextHttpServletRequest(_httpServletRequest);
		tripResource.setContextHttpServletResponse(_httpServletResponse);
		tripResource.setContextUriInfo(_uriInfo);
		tripResource.setContextUser(_user);
	}

	private static ComponentServiceObjects<StageResource>
		_stageResourceComponentServiceObjects;
	private static ComponentServiceObjects<TripResource>
		_tripResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private com.liferay.portal.kernel.model.User _user;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private UriInfo _uriInfo;

}