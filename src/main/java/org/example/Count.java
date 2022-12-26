package org.example;

public class Count {
    int count;
    public Count(int count) {
        this.count = count;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public void increase() {
        if (this.count < 10) {
            this.count ++;
        }
    }
    public void decrease() {
        if (this.count > 0) {
            this.count --;
        }
    }
    protected void Counting (Count count2) {
        increase();
        count2.setCount(this.count);
    }
}
