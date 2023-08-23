## QuickUnion Improvement weighting

### 思路

1. 修改 `QuickUnion` 避免生成较高的树
2. 记录下每个树的 高度
3. 把 `smaller` 的树接到 `larger`树下面

| 0   | 1   | 2   | 3   | 4   | 5   | 6   | 7   | 8   | 9   |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 0   | 1   | 2   | 3   | 4   | 5   | 6   | 7   | 8   | 9   |
| 1   | 1   | 1   | 1   | 1   | 1   | 1   | 1   | 1   | 1   |

`union(4, 3)`

| 0   | 1   | 2   | 3   | 4   | 5   | 6   | 7   | 8   | 9   |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 0   | 1   | 2   | `4` | 4   | 5   | 6   | 7   | 8   | 9   |
| 1   | 1   | 1   | 1   | `2` | 1   | 1   | 1   | 1   | 1   |

`union(3, 8)`

| 0   | 1   | 2   | 3   | 4   | 5   | 6   | 7   | 8   | 9   |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 0   | 1   | 2   | `4` | 4   | 5   | 6   | 7   | `4` | 9   |
| 1   | 1   | 1   | 1   | `3` | 1   | 1   | 1   | 1   | 1   |

`union(6, 5)`

| 0   | 1   | 2   | 3   | 4   | 5   | 6   | 7   | 8   | 9   |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 0   | 1   | 2   | `4` | 4   | `6` | 6   | 7   | `4` | 9   |
| 1   | 1   | 1   | 1   | `3` | 1   | `2` | 1   | 1   | 1   |

`union(9, 4)`

| 0   | 1   | 2   | 3   | 4   | 5   | 6   | 7   | 8   | 9   |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 0   | 1   | 2   | `4` | 4   | `6` | 6   | 7   | `4` | `4` |
| 1   | 1   | 1   | 1   | `4` | 1   | `2` | 1   | 1   | 1   |

`union(2, 1)`

| 0   | 1   | 2   | 3   | 4   | 5   | 6   | 7   | 8   | 9   |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 0   | `2` | 2   | `4` | 4   | `6` | 6   | 7   | `4` | `4` |
| 1   | 1   | `2` | 1   | `4` | 1   | `2` | 1   | 1   | 1   |

`union(5, 0)`

| 0   | 1   | 2   | 3   | 4   | 5   | 6   | 7   | 8   | 9   |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `6` | `2` | 2   | `4` | 4   | `6` | 6   | 7   | `4` | `4` |
| 1   | 1   | `2` | 1   | `4` | 1   | `3` | 1   | 1   | 1   |

`union(7, 2)`

| 0   | 1   | 2   | 3   | 4   | 5   | 6   | 7   | 8   | 9   |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `6` | `2` | 2   | `4` | 4   | `6` | 6   | `2` | `4` | `4` |
| 1   | 1   | `3` | 1   | `4` | 1   | `3` | 1   | 1   | 1   |

`union(6, 1)`

| 0   | 1   | 2   | 3   | 4   | 5   | 6   | 7   | 8   | 9   |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `6` | `2` | `6` | `4` | 4   | `6` | 6   | `2` | `4` | `4` |
| 1   | 1   | `3` | 1   | `4` | 1   | `6` | 1   | 1   | 1   |

`union(7, 3)` // 6 4

| 0   | 1   | 2   | 3   | 4   | 5   | 6    | 7   | 8   | 9   |
| --- | --- | --- | --- | --- | --- | ---- | --- | --- | --- |
| `6` | `2` | `6` | `4` | `6` | `6` | 6    | `2` | `4` | `4` |
| 1   | 1   | `3` | 1   | `4` | 1   | `10` | 1   | 1   | 1   |