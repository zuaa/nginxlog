CREATE TABLE "main"."nginxLog" (
"id"  INTEGER NOT NULL,
"ip"  TEXT,
"dataString"  TEXT,
"method"  TEXT,
"url"  TEXT,
"httpversion"  TEXT,
"code"  TEXT,
"pageLength"  TEXT,
"viewer"  TEXT,
PRIMARY KEY ("id" ASC)
)
;