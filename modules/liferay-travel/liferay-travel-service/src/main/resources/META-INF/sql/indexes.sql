create index IX_3E0CB11F on FOO_Stage (tripId);
create index IX_4EBADDBF on FOO_Stage (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_748DC801 on FOO_Stage (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_A4EEDD78 on FOO_Trip (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_1D0061FA on FOO_Trip (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_5A1763B3 on Travel_Stage (tripId);
create unique index IX_3356A695 on Travel_Stage (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_BF41D164 on Travel_Trip (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_E11760E6 on Travel_Trip (uuid_[$COLUMN_LENGTH:75$], groupId);