package com.zn.design;

public class Computer {
    private String mBoard;
    private String mDisplay;
    private String mOS;

    public String getmBoard() {
        return mBoard;
    }

    public void setmBoard(String mBoard) {
        this.mBoard = mBoard;
    }

    public String getmDisplay() {
        return mDisplay;
    }

    public void setmDisplay(String mDisplay) {
        this.mDisplay = mDisplay;
    }

    public String getmOS() {
        return mOS;
    }

    public void setmOS() {
        this.mOS = mOS;
    }


    public static class Builder {

        private String mBoard;
        private String mDisplay;
        private String mOS;

        public Builder setBoard(String board) {
            this.mBoard = board;
            return this;
        }

        public Builder setDisplay(String display) {
            this.mDisplay = display;
            return this;
        }

        public Builder setOs() {
            return this;
        }

        /**
         * 组装产品
         */
        private void construct(Computer computer) {
            computer.setmBoard(mBoard);
            computer.setmDisplay(mDisplay);
            computer.setmOS();
        }

        public Computer create() {
            Computer computer = new Computer();
            construct(computer);
            return computer;
        }
    }

    public static void main(String[] args){
        Computer computer = new Computer.Builder().setBoard("")
                .setDisplay("")
                .setOs()
                .create();
    }
}
