package com.example.mangary3.data.local.repository

import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import com.example.mangary3.domain.model.AnimeSong
import com.example.mangary3.domain.model.MediaType
import com.example.mangary3.domain.repository.MediaReaderRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MediaReaderRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
): MediaReaderRepository {
    val mediaFiles = mutableListOf<AnimeSong>()
    val queryUri: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        MediaStore.Files.getContentUri(
            MediaStore.VOLUME_EXTERNAL
        )
    } else {
        MediaStore.Files.getContentUri("external")
    }

    val projection = arrayOf(
        MediaStore.Files.FileColumns._ID,
        MediaStore.Files.FileColumns.DISPLAY_NAME,
        MediaStore.Files.FileColumns.MIME_TYPE,
    )

    val sortOrder = "${MediaStore.Video.Media.DISPLAY_NAME} ASC"

    override fun getAllMediaFiles(): List<AnimeSong> {
        context.contentResolver.query(
            queryUri,
            projection,
            null,
            null,
            sortOrder
        )?.use {
            cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)
            val mimeTypeColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE)

            while(cursor.moveToNext()){
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val mimeType = cursor.getString(mimeTypeColumn)
                if(name != null && mimeType != null){
                    val contentUri = Uri.withAppendedPath(
                        queryUri,
                        id.toString()
                    )

                    val mediaType = when {
                        mimeType.startsWith("video/") -> MediaType.VIDEO
                        mimeType.startsWith("audio/") -> MediaType.AUDIO
                        else -> MediaType.IMAGE

                    }

                    mediaFiles.add(
                        AnimeSong(
                            uri = contentUri,
                            name = name,
                            type = mediaType
                        )
                    )
                }

            }
        }
        return mediaFiles
    }
}