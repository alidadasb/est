package com.nw.rads.estimation

class Note {
	String title
	String description
	NoteType type

	static constraints = {
		description widget: 'textarea' 	, maxSize: 250
	}
}
