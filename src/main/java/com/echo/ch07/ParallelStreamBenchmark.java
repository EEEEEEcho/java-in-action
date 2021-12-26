package com.echo.ch07;

//import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

//@BenchmarkMode(Mode.AverageTime)
//@OutputTimeUnit(TimeUnit.MILLISECONDS)
//@Fork(value = 2,jvmArgs = {"-Xms4G","-Xmx4G"})
//@State(Scope.Thread)
//public class ParallelStreamBenchmark {
//    public static final long N = 10_000_000L;
//
//    @Benchmark
//    public long sequentialSum(){
//        return Stream.iterate(1l,i -> i+1).limit(N).reduce(0l,Long::sum);
//    }
//
//    @Benchmark
//    public long parallelSum(){
//        return LongStream.rangeClosed(1,N).parallel().reduce(0L,Long::sum);
//    }
//
//    @TearDown(Level.Invocation)
//    public void tearDown(){
//        System.gc();
//    }
//}
