export default class Stack<T> {
  private _stack: T[] = [];

  constructor(array?: T[]) {
    this._stack = array || [];
  }

  public set stack(stack: T[]) {
    this._stack = stack;
  }

  public get stack(): T[] {
    return this._stack;
  }

  public get size(): number {
    return this._stack.length;
  }

  public add(item: T): void {
    this._stack.unshift(item);
  }

  public remove(): T | undefined {
    return this._stack.pop();
  }

  public peek(index: number): T {
    return this.stack[index];
  }
}
