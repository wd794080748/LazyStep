// IPedometerService.aidl
package com.wangdong.lazystep;

// Declare any non-default types here with import statements

interface IPedometerService {
void startCount();
void stopCount();
void resetCount();
void getSetpsCount();
double getCalorie();
double getDistance();
void saveData();
void setSensitivity(double sensitivity);
double getSensitivity();
void setInterval(int interval);
int getInterval();
long getStartTimeStamp();
int getServviceStatus();
}
