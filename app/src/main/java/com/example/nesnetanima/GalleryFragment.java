package com.example.nesnetanima;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class GalleryFragment extends Fragment implements CameraBridgeViewBase.CvCameraViewListener2 {
    JavaCameraView javaCameraView;
    File caseFile;
    CascadeClassifier faceDetector;
    FileOutputStream openFileOutput;
    Mat mRgba, mGrey;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        javaCameraView = (JavaCameraView) view.findViewById(R.id.CameraView);

        if (!OpenCVLoader.initDebug()) {
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this.getContext(), baseLoaderCallback);

        } else {
            try {
                baseLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        javaCameraView.setCvCameraViewListener(this);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data, View view) {

    }
    @Override
    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat();
        mGrey = new Mat();
    }
    @Override
    public void onCameraViewStopped() {
        mRgba.release();
        mGrey.release();
    }
    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRgba=inputFrame.rgba();
        mGrey=inputFrame.gray();

        MatOfRect faceDetections=new MatOfRect();
        faceDetector.detectMultiScale(mRgba,faceDetections);
        for(Rect rect:faceDetections.toArray())
        {
            Imgproc.rectangle(mRgba,new Point(rect.x,rect.y),new Point(rect.x+rect.width,rect.y+rect.height ),
                    new Scalar(255,0,0));
        }
        return mRgba;
    }

    private final BaseLoaderCallback baseLoaderCallback = new BaseLoaderCallback(this.getContext()) {
        @Override
        public void onManagerConnected(int status) throws IOException {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    InputStream is = getResources().openRawResource(R.raw.haarcascade_frontalface_alt2);
                    File casecadeDir =  getDir(Environment.DIRECTORY_PICTURES, Context.MODE_PRIVATE);
                    caseFile = new File(casecadeDir, "haarcascade_frontalface_alt2");
                    FileOutputStream fos = new FileOutputStream(caseFile);
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                    }
                    is.close();
                    fos.close();
                    faceDetector = new CascadeClassifier(caseFile.getAbsolutePath());
                    if (faceDetector.empty()) {
                        faceDetector = null;
                    } else {
                        casecadeDir.delete();
                    }
                    javaCameraView.enableView();
                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }

        public File getDir(String directoryPictures, int modePrivate) {
            return getContext().getDir(directoryPictures, modePrivate);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return null;
        }
    };




}