/*
 * Copyright (C) <2020>  <aalx crystal>
 * <p>
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * <p>
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along with this program.  If not,
 * see <http://www.gnu.org/licenses/>.
 */

package com.aalx.mt_01_concept.c02_synchronized;

/**
 * <p>Title: Sync_Synchronized</p>
 * <p>Description: ${}</p>
 * 线程同步
 *      即当有一个线程在对内存进行操作时，其他线程都不可以对这个内存地址进行操作，直到该线程完成操作，
 *      其他线程才能对该内存地址进行操作，而其他线程又处于等待状态，实现线程同步的方法有很多，临界区
 *      对象就是其中一种。
 * 预习
 *      Java对象头和monitor(监视器)是实现synchronized的基础
 *      Hotspot虚拟机的对象头主要包括两部分数据：Mark Word（标记字段）、Klass Pointer（类型指针）。
 *
 *      类型指针是是对象指向它的类元数据的指针，虚拟机通过这个指针来确定这个对象是哪个类的实例；
 *      标记字段用于存储对象自身的运行时数据；
 *
 *      monitor监视器有点像只能装一个线程的盒子，有enter和exit方法
 *
 * synchronized锁升级过程
 *      1、检测对象头的标记字段里面是不是当前线程的ID，如果是，表示当前线程处于偏向锁
 *      2、如果不是，则使用CAS将当前线程的ID替换标记字段，如果成功则表示当前线程获得偏向锁，置偏向标志位1
 *      3、如果失败，则说明发生竞争，撤销偏向锁，进而升级为轻量级锁。
 *      4、当前线程使用CAS将对象头的标记字段替换为锁记录指针，如果成功，当前线程获得锁
 *      5、如果失败，表示其他线程竞争锁，当前线程便尝试使用自旋来获取锁。
 *      6、如果自旋成功则依然处于轻量级状态。
 *      7、如果自旋失败，则升级为重量级锁。
 *
 * synchronized的同步与非同步方法
 *      锁静态方法：类锁，锁住整个类
 *      锁实例方法：实例锁，锁当前实例对象
 *      同步代码块：
 * synchronized重入
 *      自己可以再次获取自己的内部锁。比如一个线程获得了某个对象的锁，此时锁还没释放，当再次获取这个对象锁
 *      的时候还可以获取。（如果不能获取，就会造成死锁）
 * @author aalx
 * @date 2020/4/15 12:01
 */
public class Sync_Synchronized {

    private int count;

    public int add(int n){
        synchronized (this){
            count += n;
        }
        return count;
    }
}
