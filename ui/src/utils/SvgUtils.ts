export const handleSvgClick = (searchNodeName: string, consumer: (el: HTMLElement) => unknown) => {
  return (event: {
    target: HTMLElement
  }) => {
    let g;
    let current: HTMLElement | null = event.target;
    while (current && current.nodeName !== 'svg') {
      if (current.nodeName === searchNodeName) {
        g = current;
        break;
      }
      current = current.parentElement;
    }
    if (!g) return;

    consumer(g);
  }
}