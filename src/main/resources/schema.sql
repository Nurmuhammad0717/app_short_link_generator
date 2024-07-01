create unique index if not exists user_email_unix
    on users (email) where deleted = false;

create unique index if not exists link_short_url_unix
    on link (short_url) where deleted = false;