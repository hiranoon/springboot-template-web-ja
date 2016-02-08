

| controller | 画面単位 |
| web        | 画面単位 |
| service    | 画面単位 |
| repository | テーブル単位 |
| domain     | テーブル単位 |


### DBスキーマ

`players` 、 `nationarities` のように、複数形としています。
Ruby on Rails と同じような命名としました。

カラムは同じ命名は同じモノを指すようにしています。
例えば、PK は `id` ではなく、 `player_id` 、 `nationarity_id` のように、別の意味をあらわすものは、命名も別になる（逆に言うと命名が同じであれば同じ意味のものを指す）ようにしています。

