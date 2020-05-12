package com.github.anhTom2000.utils.Generator.impl;

import com.github.anhTom2000.utils.Generator.IdGenerator;

/**
 * @Description : TODO          基于雪花算法的ID生成器
 * @Author :    yangguang
 * @Date :      2019/12/13
 */
public class SnowflakeIdGenerator implements IdGenerator {



    //起始的时间戳
    private final long startStamp = 964108800000L;

    /**
     * 每一部分占用的位数
     */
    //序列号占用的位数
    private final long sequenceBit = 12L;
    //机器号占用的位数
    private final long machineBit = 5L;
    //数据中心号占用的位数
    private final long dataCenterBit = 5L;

    /**
     * 每一部分的最大值
     */
    private final long maxSequence = -1L ^ (-1L << sequenceBit);
    private final long maxMachine = -1L ^ (-1L << machineBit);
    private final long maxDataCenter = -1L ^ (-1L << dataCenterBit);

    /**
     * 每一部分向左移的位数
     */
    private final long machineLeftMove = sequenceBit;
    private final long dataCenterLeftMove = sequenceBit + machineBit;
    private final long timestampLeftMove = dataCenterLeftMove + dataCenterBit;

    //序列号
    private long sequence = 0L;

    //数据中心ID
    private final long dataCenterId;

    //机器号ID
    private final long machineId;

    //上一次的时间戳
    private long lastTimeStamp = -1L;

    /**
     * @Method
     * Description:
     *  创建一个唯一的id生成器
     * @Author weleness
     *
     * @Return
     */
    private static  class idGenHolder{
        private static final SnowflakeIdGenerator instance = new SnowflakeIdGenerator();
    }

    /**
     * @Method
     * Description:
     *  初始值
     * @Author weleness
     *
     * @Return
     */
    public SnowflakeIdGenerator() {
        this(1L,1L);
    }

    /**
     * @Method
     * Description:
     *   返回一个雪花id生成器对象，生成唯一id
     * @Author weleness
     *
     * @Return SnowflakeIdGenerator
     */
    public static SnowflakeIdGenerator getInstance(){
        return idGenHolder.instance;
    }


    /**
     * 构造函数
     * @param dataCenterId 数据中心ID(0~31)
     * @param machineId    机器号ID(0~31)
     */
    public SnowflakeIdGenerator(long dataCenterId, long machineId)
    {
        if (dataCenterId > maxDataCenter || dataCenterId < 0) {
            throw new IllegalArgumentException("数据中心号不能超出范围");
        }
        if (machineId > maxMachine || machineId < 0) {
            throw new IllegalArgumentException("机器号不能超出范围");
        }
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }


    /**
     * 生成ID
     * @return long 类型的id
     */
    @Override
    public synchronized Long generateId()
    {
        long curTS = System.currentTimeMillis();
        //如果当前时间小于上次获取的时间戳,那么说明系统时钟出现问题
        if(curTS < lastTimeStamp)
        {
            throw new RuntimeException("系统时钟异常,无法生成ID");
        }
        //如果当前时间等于上次时间,那么阻塞一会
        if(curTS == lastTimeStamp)
        {
            //在相同毫秒内递增序列号
            sequence = (sequence + 1L) & maxSequence;
            //如果毫秒内达到最大值,那么阻塞获取下一个时间戳
            if(sequence == 0L)
            {
                curTS = getNextTimestamp();
            }
        }
        //如果当前时间大于上次时间,将序列置为0
        else
        {
            sequence = 0L;
        }

        lastTimeStamp = curTS;

        return (curTS - startStamp) << timestampLeftMove |
                dataCenterId << dataCenterLeftMove |
                machineId << machineLeftMove |
                sequence;
    }


    /**
     * 获取下一个时间戳
     * @return 下一个时间戳
     */
    private long getNextTimestamp()
    {
        long curTS = System.currentTimeMillis();
        //如果当前时间戳仍然小于上一次的时间戳,就一直阻塞获取
        while (curTS <= lastTimeStamp)
        {
            curTS = System.currentTimeMillis();
        }
        return curTS;
    }
}
