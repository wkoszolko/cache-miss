# cache-miss
```shell script
Benchmark                                                    (size)  Mode  Cnt  Score   Error  Units
CacheMissBenchmark.computeAllElements                       8000000  avgt  200  4,907 ± 0,019  ms/op
CacheMissBenchmark.computeHalfOfTheArray                    8000000  avgt  200  3,126 ± 1,016  ms/op
CacheMissBenchmark.computeOneElementForEachCacheLine        8000000  avgt  200  4,936 ± 0,112  ms/op
CacheMissBenchmark.computeOneElementForEveryOtherCacheLine  8000000  avgt  200  3,864 ± 0,034  ms/op
```