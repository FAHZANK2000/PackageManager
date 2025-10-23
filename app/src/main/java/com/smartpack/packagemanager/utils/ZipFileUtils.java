/*
 * Copyright (C) 2021-2022 sunilpaulmathew <sunil.kde@gmail.com>
 *
 * This file is part of Package Manager, a simple, yet powerful application
 * to manage other application installed on an android device.
 *
 */

package com.smartpack.packagemanager.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ProgressBar;

import com.smartpack.packagemanager.dialogs.ProgressDialog;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;

import java.io.File;
import java.io.IOException;
import java.util.List;

import in.sunilpaulmathew.sCommon.FileUtils.sFileUtils;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on February 19, 2023
 */
public class ZipFileUtils extends ZipFile {

    private ProgressBar mProgressBar = null;
    private ProgressDialog mProgressDialog = null;

    public ZipFileUtils(String zipFile) {
        super(zipFile);
    }

    public ZipFileUtils(Uri uri, Context context) throws IOException {
        super(getFileFromUri(uri, context));
    }

    public void setProgress(ProgressBar progressBar) {
        mProgressBar = progressBar;
    }

    public void setProgress(ProgressDialog progressDialog) {
        mProgressDialog = progressDialog;
    }

    private void setProgress() {
        if (mProgressBar != null) {
            if (mProgressBar.getProgress() < mProgressBar.getMax()) {
                mProgressBar.setProgress(mProgressBar.getProgress() + 1);
            } else {
                mProgressBar.setProgress(0);
            }
        } else if (mProgressDialog != null) {
            mProgressDialog.updateProgress(1);
        }
    }

    private void setProgressMax(int max) {
        if (mProgressBar != null) {
            mProgressBar.setMax(max);
        } else if (mProgressDialog != null) {
            mProgressDialog.setMax(max);
        }
    }

    public void unzip(String path) throws ZipException {
        setProgressMax(getFileHeaders().size());
        for (FileHeader fileHeaders : getFileHeaders()) {
            extractFile(fileHeaders, path);
            setProgress();
        }
    }

    public void zip(List<File> files) throws ZipException {
        setProgressMax(files.size());
        for (File file : files) {
            addFile(file);
            setProgress();
        }
    }

    private static File getFileFromUri(Uri uri, Context context) throws IOException {
        // If it's already a file:// URI, return directly
        if ("file".equalsIgnoreCase(uri.getScheme())) {
            return new File(uri.getPath());
        } else {
            File tempFile = new File(context.getCacheDir(), "tmpZip.zip");
            sFileUtils.copy(uri, tempFile, context);
            return tempFile;
        }
    }

}