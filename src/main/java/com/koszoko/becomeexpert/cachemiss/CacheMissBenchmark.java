package com.koszoko.becomeexpert.cachemiss;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class CacheMissBenchmark {

    @State(Scope.Benchmark)
    public static class ExecutionPlan {
        @Param({ "10000000" })
        int size;
        double[] array;
        @Setup(Level.Invocation)
        public void setUp() {
            array = new double[size];
            Arrays.fill(array, ThreadLocalRandom.current().nextDouble());
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void computeAllElements(ExecutionPlan plan) {
        for (int i=0 ; i < plan.size ; i++) {
            plan.array[i] *= 3;
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void computeOneElementForEachCacheLine(ExecutionPlan plan) {
        for (int i=0 ; i < plan.size ; i+=8) {
            plan.array[i] *= 3;
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void computeHalfOfTheArray(ExecutionPlan plan) {
        for (int i=0 ; i < plan.size/2 ; i++) {
            plan.array[i] *= 3;
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void computeOneElementForEveryOtherCacheLine(ExecutionPlan plan) {
        for (int i=0 ; i < plan.size ; i+=16) {
            plan.array[i] *= 3;
        }
    }
}
