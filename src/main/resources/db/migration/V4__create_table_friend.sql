create table if not exists friend (
    friend_id uuid not null,
    requester_id uuid not null,
    receiver_id uuid not null,
    occurred_on timestamp with time zone NOT NULL,
    status varchar(50),
    CONSTRAINT friend_id PRIMARY KEY (friend_id),
    CONSTRAINT requester_id_fk FOREIGN KEY (requester_id) REFERENCES profile(profile_id),
    CONSTRAINT receiver_id_fk FOREIGN KEY (receiver_id) REFERENCES profile(profile_id));
