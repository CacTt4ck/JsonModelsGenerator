package com.cactt4ck.jsonmodelsgenerator.utils;

import java.awt.*;

public class LabelledImage {

    private String label;
    private Image image;

    public LabelledImage(String label, Image image) {
        this.image = image;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public Image getImage() {
        return image;
    }
}
