package com.wandrell.tabletop.punkapocalyptic.punkabuilder.service;

import java.io.File;

import javafx.stage.Stage;

import com.wandrell.tabletop.punkapocalyptic.model.unit.Gang;

public interface FileService {

    public File getFileToPrintGangOnDesktop(final Stage stage);

    public String getTitleImagePath();

    public void saveGang(final Gang gang, final File file);

}
