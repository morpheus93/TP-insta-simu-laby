package data;

import tools.Type;

public class Case {
	private Type status;
	private boolean downIsOpen;
	private boolean rightIsOpen;
	
	Case(final boolean downIsOpen, final boolean rightIsOpen, final Type status) {
		this.downIsOpen = downIsOpen;
		this.rightIsOpen = rightIsOpen;
		this.status = status;
	}

	public Type getStatus() {
		return status;
	}

	public void setStatus(final Type status) {
		this.status = status;
	}

	public boolean isDownIsOpen() {
		return downIsOpen;
	}

	public void setDownIsOpen(final boolean downIsOpen) {
		this.downIsOpen = downIsOpen;
	}

	public boolean isRightIsOpen() {
		return rightIsOpen;
	}

	public void setRightIsOpen(final boolean rightIsOpen) {
		this.rightIsOpen = rightIsOpen;
	}
}
