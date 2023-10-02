package `in`.day1.a7minutesworkout

class ExerciseModel(
    private var id : Int,
    private var name: String,
    private var image: Int,
    private var isComplete: Boolean,
    private var isSelected: Boolean) {
//Making functions for getting and putting the values
    fun getId() : Int {
        return this.id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getName() : String {
        return this.name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getImage() : Int {
        return this.image
    }

    fun setImage(image: Int) {
        this.image = image
    }

    fun getCompletedStatus() : Boolean {
        return this.isComplete
    }

    fun setCompletedStatus(isComplete: Boolean) {
        this.isComplete=isComplete
    }

    fun getSelectedStatus(): Boolean {
        return this.isSelected
    }

    fun setSelectedStatus(isSelected: Boolean) {
        this.isSelected = isSelected
    }
}