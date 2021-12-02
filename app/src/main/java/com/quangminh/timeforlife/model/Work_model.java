package com.quangminh.timeforlife.model;

public class Work_model {

    String note;
    int typeNote;
    int workProgress;

    public Work_model(String note, int typeNote, int workProgress) {
        this.note = note;
        this.typeNote = typeNote;
        this.workProgress = workProgress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getTypeNote() {
        return typeNote;
    }

    public void setTypeNote(int typeNote) {
        this.typeNote = typeNote;
    }

    public int getWorkProgress() {
        return workProgress;
    }

    public void setWorkProgress(int workProgress) {
        this.workProgress = workProgress;
    }
}
