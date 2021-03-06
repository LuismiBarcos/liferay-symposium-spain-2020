package com.liferay.travel.rest.internal.graphql.servlet.v1_0;

import com.liferay.portal.vulcan.graphql.servlet.ServletData;
import com.liferay.travel.rest.internal.graphql.mutation.v1_0.Mutation;
import com.liferay.travel.rest.internal.graphql.query.v1_0.Query;
import com.liferay.travel.rest.resource.v1_0.StageResource;
import com.liferay.travel.rest.resource.v1_0.TripResource;

import javax.annotation.Generated;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author Javier de Arcos
 * @generated
 */
@Component(immediate = true, service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Mutation.setStageResourceComponentServiceObjects(
			_stageResourceComponentServiceObjects);
		Mutation.setTripResourceComponentServiceObjects(
			_tripResourceComponentServiceObjects);

		Query.setStageResourceComponentServiceObjects(
			_stageResourceComponentServiceObjects);
		Query.setTripResourceComponentServiceObjects(
			_tripResourceComponentServiceObjects);
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/liferay-travel-rest-graphql/v1_0";
	}

	@Override
	public Query getQuery() {
		return new Query();
	}

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<StageResource>
		_stageResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<TripResource>
		_tripResourceComponentServiceObjects;

}