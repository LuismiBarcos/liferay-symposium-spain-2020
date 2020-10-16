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

package com.liferay.travel.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.travel.model.Stage;
import com.liferay.travel.model.StageModel;
import com.liferay.travel.model.StageSoap;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the Stage service. Represents a row in the &quot;FOO_Stage&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>StageModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link StageImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StageImpl
 * @generated
 */
@JSON(strict = true)
public class StageModelImpl extends BaseModelImpl<Stage> implements StageModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a stage model instance should use the <code>Stage</code> interface instead.
	 */
	public static final String TABLE_NAME = "FOO_Stage";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"stageId", Types.BIGINT},
		{"name", Types.VARCHAR}, {"description", Types.VARCHAR},
		{"place", Types.VARCHAR}, {"image", Types.VARCHAR},
		{"tripId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("stageId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("place", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("image", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("tripId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table FOO_Stage (uuid_ VARCHAR(75) null,stageId LONG not null primary key,name VARCHAR(75) null,description VARCHAR(75) null,place VARCHAR(75) null,image STRING null,tripId LONG)";

	public static final String TABLE_SQL_DROP = "drop table FOO_Stage";

	public static final String ORDER_BY_JPQL = " ORDER BY stage.stageId ASC";

	public static final String ORDER_BY_SQL = " ORDER BY FOO_Stage.stageId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long TRIPID_COLUMN_BITMASK = 1L;

	public static final long UUID_COLUMN_BITMASK = 2L;

	public static final long STAGEID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static Stage toModel(StageSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Stage model = new StageImpl();

		model.setUuid(soapModel.getUuid());
		model.setStageId(soapModel.getStageId());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setPlace(soapModel.getPlace());
		model.setImage(soapModel.getImage());
		model.setTripId(soapModel.getTripId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static List<Stage> toModels(StageSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Stage> models = new ArrayList<Stage>(soapModels.length);

		for (StageSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public StageModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _stageId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setStageId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stageId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Stage.class;
	}

	@Override
	public String getModelClassName() {
		return Stage.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Stage, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Stage, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Stage, Object> attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Stage)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Stage, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Stage, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept((Stage)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Stage, Object>> getAttributeGetterFunctions() {
		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Stage, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Stage>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Stage.class.getClassLoader(), Stage.class, ModelWrapper.class);

		try {
			Constructor<Stage> constructor =
				(Constructor<Stage>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<Stage, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Stage, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Stage, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Stage, Object>>();
		Map<String, BiConsumer<Stage, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Stage, ?>>();

		attributeGetterFunctions.put("uuid", Stage::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<Stage, String>)Stage::setUuid);
		attributeGetterFunctions.put("stageId", Stage::getStageId);
		attributeSetterBiConsumers.put(
			"stageId", (BiConsumer<Stage, Long>)Stage::setStageId);
		attributeGetterFunctions.put("name", Stage::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<Stage, String>)Stage::setName);
		attributeGetterFunctions.put("description", Stage::getDescription);
		attributeSetterBiConsumers.put(
			"description", (BiConsumer<Stage, String>)Stage::setDescription);
		attributeGetterFunctions.put("place", Stage::getPlace);
		attributeSetterBiConsumers.put(
			"place", (BiConsumer<Stage, String>)Stage::setPlace);
		attributeGetterFunctions.put("image", Stage::getImage);
		attributeSetterBiConsumers.put(
			"image", (BiConsumer<Stage, String>)Stage::setImage);
		attributeGetterFunctions.put("tripId", Stage::getTripId);
		attributeSetterBiConsumers.put(
			"tripId", (BiConsumer<Stage, Long>)Stage::setTripId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getStageId() {
		return _stageId;
	}

	@Override
	public void setStageId(long stageId) {
		_stageId = stageId;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@JSON
	@Override
	public String getPlace() {
		if (_place == null) {
			return "";
		}
		else {
			return _place;
		}
	}

	@Override
	public void setPlace(String place) {
		_place = place;
	}

	@JSON
	@Override
	public String getImage() {
		if (_image == null) {
			return "";
		}
		else {
			return _image;
		}
	}

	@Override
	public void setImage(String image) {
		_image = image;
	}

	@JSON
	@Override
	public long getTripId() {
		return _tripId;
	}

	@Override
	public void setTripId(long tripId) {
		_columnBitmask |= TRIPID_COLUMN_BITMASK;

		if (!_setOriginalTripId) {
			_setOriginalTripId = true;

			_originalTripId = _tripId;
		}

		_tripId = tripId;
	}

	public long getOriginalTripId() {
		return _originalTripId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, Stage.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Stage toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Stage>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		StageImpl stageImpl = new StageImpl();

		stageImpl.setUuid(getUuid());
		stageImpl.setStageId(getStageId());
		stageImpl.setName(getName());
		stageImpl.setDescription(getDescription());
		stageImpl.setPlace(getPlace());
		stageImpl.setImage(getImage());
		stageImpl.setTripId(getTripId());

		stageImpl.resetOriginalValues();

		return stageImpl;
	}

	@Override
	public int compareTo(Stage stage) {
		long primaryKey = stage.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Stage)) {
			return false;
		}

		Stage stage = (Stage)object;

		long primaryKey = stage.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		StageModelImpl stageModelImpl = this;

		stageModelImpl._originalUuid = stageModelImpl._uuid;

		stageModelImpl._originalTripId = stageModelImpl._tripId;

		stageModelImpl._setOriginalTripId = false;

		stageModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Stage> toCacheModel() {
		StageCacheModel stageCacheModel = new StageCacheModel();

		stageCacheModel.uuid = getUuid();

		String uuid = stageCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			stageCacheModel.uuid = null;
		}

		stageCacheModel.stageId = getStageId();

		stageCacheModel.name = getName();

		String name = stageCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			stageCacheModel.name = null;
		}

		stageCacheModel.description = getDescription();

		String description = stageCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			stageCacheModel.description = null;
		}

		stageCacheModel.place = getPlace();

		String place = stageCacheModel.place;

		if ((place != null) && (place.length() == 0)) {
			stageCacheModel.place = null;
		}

		stageCacheModel.image = getImage();

		String image = stageCacheModel.image;

		if ((image != null) && (image.length() == 0)) {
			stageCacheModel.image = null;
		}

		stageCacheModel.tripId = getTripId();

		return stageCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Stage, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Stage, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Stage, Object> attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Stage)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<Stage, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Stage, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Stage, Object> attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Stage)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Stage>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private String _originalUuid;
	private long _stageId;
	private String _name;
	private String _description;
	private String _place;
	private String _image;
	private long _tripId;
	private long _originalTripId;
	private boolean _setOriginalTripId;
	private long _columnBitmask;
	private Stage _escapedModel;

}