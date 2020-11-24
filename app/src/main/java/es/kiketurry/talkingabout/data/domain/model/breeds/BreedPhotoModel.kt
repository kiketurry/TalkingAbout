package es.kiketurry.talkingabout.data.domain.model.breeds

import es.kiketurry.talkingabout.data.domain.model.BaseModel

class BreedPhotoModel(var url: String) : BaseModel() {
    companion object {
        fun breedsPhotoModelArrayListToStringArrayList(breedsPhotoModelArrayList: ArrayList<BreedPhotoModel>): ArrayList<String> {
            val urlArrayList: ArrayList<String> = ArrayList()
            for (breedPhotoModel in breedsPhotoModelArrayList) {
                urlArrayList.add(breedPhotoModel.url)
            }
            return urlArrayList
        }
    }
}