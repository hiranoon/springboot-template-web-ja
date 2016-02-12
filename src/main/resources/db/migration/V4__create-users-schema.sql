create table users (
    username            varchar(255)    primary key,
    encrypted_password  varchar(255),
    sign_in_count       int             default 0 not null,
    current_sign_in_at  datetime,
    last_sign_in_at     datetime,
    current_sign_in_ip  varchar(255),
    last_sign_in_ip     varchar(255),
    created_at          datetime,
    updated_at          datetime
);
/*
 * username             : ログインID と同義. ログイン時に使用
 * encrypted_password   : 暗号化したパスワード. ログイン時に使用.
 *
 * ## Userinfo
 * email        : ユーザーのメールアドレス
 * first_name   : 名
 * family_name  : 姓
 *
 * ## Trackable
 * sign_in_count        : ログイン回数.
 * current_sign_in_at   : 直近でログインした時刻
 * last_sign_in_at      : 直近でログアウトした時刻
 * current_sign_in_ip   : 直近でログインしたIPアドレス
 * last_sign_in_ip      : 直近でログアウトしたIPアドレス
 *
 * -- 以下、要否検討 --
 *
 * ## Recoverable
 * reset_password_token : パスワードリセット用URLで利用する為のトークン.
 * reset_password_sent_at : パスワードリセットを行った時刻. 有効期限に利用.
 *  reset_password_token    varchar(255),
 *  reset_password_sent_at  datetime,
 *
 * ## Rememberable
 * remember_created_at : クッキーが有効な時間を判定するために利用.
 *  remember_created_at     datetime,
 *
 * ## Confirmable
 * アカウント登録時に確認メールを送信し、ログイン時に確認されているかをチェックします.
 *  confirmation_token      varchar(255),
 *  confirmed_at            datetime,
 *  confirmation_sent_at    datetime,
 *  unconfirmed_email       varchar(255),
 *
 * ## Recoverable
 * パスワードを紛失した際に、パスワードのリセットを行うことができます.
 *  reset_password_token    varchar(255),
 *  reset_password_sent_at  datetime,
 */
