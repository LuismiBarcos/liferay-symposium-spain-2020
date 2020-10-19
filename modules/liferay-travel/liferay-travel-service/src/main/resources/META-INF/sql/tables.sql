create table FOO_Stage (
	uuid_ VARCHAR(75) null,
	stageId LONG not null primary key,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	place VARCHAR(75) null,
	image STRING null,
	tripId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null
);

create table FOO_Trip (
	uuid_ VARCHAR(75) null,
	companyId LONG,
	tripId LONG not null primary key,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	startingDate DATE null,
	image STRING null,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null
);

create table Travel_Stage (
	uuid_ VARCHAR(75) null,
	stageId LONG not null primary key,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	place VARCHAR(75) null,
	image STRING null,
	tripId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null
);

create table Travel_Trip (
	uuid_ VARCHAR(75) null,
	tripId LONG not null,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	startingDate DATE null,
	image STRING null,
	userId LONG,
	userName VARCHAR(75) null,
	resourcePrimKey LONG not null,
	groupId LONG,
	companyId LONG,
	primary key (tripId, resourcePrimKey)
);