package com.example.printjobexample;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import com.clover.sdk.v1.printer.ReceiptContract;

import java.io.FileNotFoundException;

public class ReceiptRegistrationProvider extends ContentProvider {
    public static final String AUTHORITY = "com.example.printjobexample;";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    public static final String CONTENT_DIRECTORY_TEXT = "text";
    public static final Uri CONTENT_URI_TEXT = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_DIRECTORY_TEXT);

    private static final int TEXT = 0;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static String addOnText = "";

    static {
        uriMatcher.addURI(AUTHORITY, CONTENT_DIRECTORY_TEXT, TEXT);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    public static void updateReceiptAddOnText(String text) {
        ReceiptRegistrationProvider.addOnText = text;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings2, String s2) {
        switch (uriMatcher.match(uri)) {
            case TEXT:
                MatrixCursor cursor = new MatrixCursor(new String[]{ReceiptContract.Text._ID, ReceiptContract.Text.TEXT});

                cursor.addRow(new Object[]{Integer.valueOf(0), addOnText});
                return cursor;

            default:
                throw new IllegalArgumentException("unknown uri: " + uri);
        }
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case TEXT:
                return ReceiptContract.Text.CONTENT_TYPE;
            default:
                throw new IllegalArgumentException("unknown URI " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {
        return null;
    }
}